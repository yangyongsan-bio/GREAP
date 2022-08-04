package com.lyy.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import ch.ethz.ssh2.Connection;
import ch.ethz.ssh2.Session;
import ch.ethz.ssh2.StreamGobbler;

public class Linux_java {
	private String ip = "XXXXXXX";
    private int port = 22;
    private String userName = "XXX";
    private String pwd = "XXX";

    private Connection connection = new Connection(ip, port);

    public List<String> getExecCommand(String string) throws IOException {
        List<String> result = new ArrayList<>();
        connection.connect();// ����
        boolean authenticateWithPassword = connection.authenticateWithPassword(userName, pwd);// ��֤
        if (!authenticateWithPassword || !login()) {
            throw new IOException("Authentication failed.");
        } else {
            Session session = connection.openSession();
            session.execCommand(string, "utf-8");
            StreamGobbler stdout = new StreamGobbler(session.getStdout());
            StreamGobbler stderr = new StreamGobbler(session.getStderr());
            BufferedReader stdoutBuffere = new BufferedReader(new InputStreamReader(stdout));
            BufferedReader stderrstdoutBuffere = new BufferedReader(new InputStreamReader(stderr));
            for (String line = stdoutBuffere.readLine(); line != null; line = stdoutBuffere.readLine()) {
                result.add(line + "\n");
            }
            for (String line = stderrstdoutBuffere.readLine(); line != null; line = stderrstdoutBuffere.readLine()) {
                result.add(line);
            }
           
            stdoutBuffere.close();
            stderrstdoutBuffere.close();
            session.close();
        }
        connection.close();
        return result;
    }

    private Boolean login() {
        boolean flg = false;
        try {
            connection = new Connection(ip);
            connection.connect();// ���� //�ж�����Ƿ��Ѿ���֤
            if (!connection.isAuthenticationComplete()) {
                // ��������ֹ���̵߳���ʱ�̼߳��жϲ�һ�£����³����ظ���֤
                synchronized (this) {
                    if (!connection.isAuthenticationComplete()) { // ���������֤
                        flg = connection.authenticateWithPassword(userName, pwd);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return flg;
    }
}
