package cn.bestcondition.android_learn.compile;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.TransferQueue;

import cn.bestcondition.android_learn.model.Action;
import cn.bestcondition.android_learn.model.Function;
import cn.bestcondition.android_learn.model.If;
import cn.bestcondition.android_learn.model.Script;
import cn.bestcondition.android_learn.model.Statement;
import cn.bestcondition.android_learn.model.Wait;

public class Compile {
    //4空格缩进
    private static final Retract retract = new Retract("    ");
    private final Reader reader;

    public Compile(Reader reader) {
        this.reader = reader;
    }

    public Compile(String codeString) {
        this.reader = new StringReader(codeString);
    }

    public Compile(File codeFile) throws FileNotFoundException {
        this.reader = new FileReader(codeFile);
    }

    public Script compile() throws IOException {
        List<String> code = readerToLines(this.reader);
        return compile(code);
    }

    /*
    把一个reader分成行，装在一个List里面，而且排除了空行
     */
    private List<String> readerToLines(Reader reader) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(reader);
        List<String> lines = new LinkedList<>();
        String line = bufferedReader.readLine();
        while (line != null) {
            if (!line.trim().equals("")) {
                lines.add(line);
            }
            line = bufferedReader.readLine();
        }
        return lines;
    }

    private Script compile(List<String> lines) {
        Deque<String> deque = new LinkedList<>(lines);
        List<Function> functionList = new LinkedList<>();
        if (deque.isEmpty()) {
            return new Script(functionList);
        }

        do {
            String line = deque.pop().trim();
            assert line.length() >= 2;
            assert line.charAt(line.length() - 1) == '：';
            String functionName = line.substring(0, line.length() - 1);
            List<Statement> statementList = getSubStatementList(deque);
            Function function = new Function(functionName, statementList);
            functionList.add(function);

        } while (!deque.isEmpty());
        return new Script(functionList);
    }

    private List<Statement> getStatementList(List<String> lines) {
        Deque<String> deque = new LinkedList<>(lines);
        List<Statement> statementList = new LinkedList<>();
        if (deque.isEmpty()) {
            return statementList;
        }

        do {
            /*
            为什么可以pop？
            走到这一步deque肯定不是空的，
            前面有判空，
            所以可以直接pop

            为什么trim？不害怕把缩进信息整丢吗？
            放心这一行代码不可能有前缀，
            有前缀的在getSubStatementList方法里处理，
            是一条Action或者Function、If、Wait的头，
            我trim的主要是后面可能的空格，
            如果不放心可以加个assert
             */
            String line = deque.pop().trim();

            /*
            为什么代码长度>=2？
            因为如果是If，条件至少一个字，加上"如果："，至少有4个字；
            如果是Wait，条件字少一个字，加上"当时："，至少4个字；
            如果是Function，方法名至少一个字，加上"："，至少有2个字，当然，这里面不可能有Function；
            代码长度小于2的只有Action语句，
            else里面确实是处理Action的。
             */
            if (line.length() >= 2) {
                if (line.startsWith("如果")) {
                    assert line.endsWith("：") : line + "没有：结尾";

                    String expression = line.substring(2, line.length() - 1).trim();
                    List<Statement> ifStatementList = getSubStatementList(deque);
                    if (!deque.isEmpty()) {
                        String p = deque.peek();
                        assert p != null;
                        if (p.trim().equals("否则：")) {
                            deque.pop();
                        }
                    }
                    List<Statement> elseStatementList = getSubStatementList(deque);
                    If ifBlock = new If(
                            expression,
                            ifStatementList,
                            elseStatementList
                    );
                    statementList.add(ifBlock);
                } else if (line.startsWith("当")) {
                    assert line.endsWith("时：") : line + "Wait语句后面没有时：";
                    String expression = line.substring(1, line.length() - 2).trim();
                    List<Statement> waitStatementList = getSubStatementList(deque);
                    Wait waitBlock = new Wait(
                            expression,
                            waitStatementList
                    );
                    statementList.add(waitBlock);
                } else {
                    statementList.add(new Action(line));
                }
            } else {
                statementList.add(new Action(line));
            }
        } while (!deque.isEmpty());
        return statementList;
    }

    /*
    接下来的代码成一块，
    去掉一层缩进，
    返回这一块的语句列表
     */
    private List<Statement> getSubStatementList(Deque<String> deque) {
        List<String> subLines = new LinkedList<>();
        while (true) {
            if (deque.isEmpty()) {
                break;
            }
            String line = deque.peek();
            assert line != null;
            if (retract.hasRetract(line)) {
                line = retract.offRetract(line);
                subLines.add(line);
            } else {
                break;
            }
            deque.pop();
        }
        return getStatementList(subLines);
    }


}
