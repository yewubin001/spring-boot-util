package com.example.springboot.utils;

import net.schmizz.sshj.SSHClient;
import net.schmizz.sshj.sftp.SFTPClient;
import net.schmizz.sshj.transport.verification.PromiscuousVerifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;

/**
 * @Auther: yewub
 * @Date: 2019/5/27 16:17
 * @Description:
 */
public class FtpHandler {
    private SftpConfig sftpConfig;
    private static final Logger LOGGER = LoggerFactory.getLogger(FtpHandler.class);

    public void setSftpConfig(SftpConfig sftpConfig) {
        this.sftpConfig = sftpConfig;
    }

    /**
     * 从sftp上获取文件
     *
     * @param filePath ftp上的文件路径
     * @return
     * @throws IOException
     */
    public void downloadFile(String filePath, File distFile) throws IOException {
        try (SSHClient sshClient = new SSHClient()) {
            configSshClient(sshClient);
            try (SFTPClient sftpClient = sshClient.newSFTPClient()) {
                sftpClient.get(filePath, distFile.getAbsolutePath());
            }
        }
    }

    private void configSshClient(SSHClient sshClient) throws IOException {
        sshClient.addHostKeyVerifier(new PromiscuousVerifier());
        sshClient.connect(sftpConfig.getHostName(), sftpConfig.getPort());
        sshClient.authPassword(sftpConfig.getUserName(), sftpConfig.getPassword());
    }


    /**
     * 上传本地文件到ftp对应的地方
     *
     * @param localFilePath  本地文件路径
     * @param remoteFilePath 保存到ftp上的文件路径, 路径需要使用 "/"分隔
     * @throws IOException
     */
    public void uploadFile(String localFilePath, String remoteFilePath) throws IOException {
        try (SSHClient sshClient = new SSHClient()) {
            configSshClient(sshClient);
            try (SFTPClient sftpClient = sshClient.newSFTPClient()) {
                int endIndex = remoteFilePath.lastIndexOf("/");
                if(endIndex > 0) {
                    String dir = remoteFilePath.substring(0, endIndex);
                    try {
                        LOGGER.info("upload dir is {}", dir);
                        sftpClient.ls(dir);
                    } catch (IOException e) {
                        sftpClient.mkdir(dir);
                    }
                }

                sftpClient.put(localFilePath, remoteFilePath);
            }
        }
    }

    public static class SftpConfig {
        private String hostName;
        private int port;
        private String userName;
        private String password;

        public String getHostName() {
            return hostName;
        }

        public void setHostName(String hostName) {
            this.hostName = hostName;
        }

        public int getPort() {
            return port;
        }

        public void setPort(int port) {
            this.port = port;
        }

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }
    }
}