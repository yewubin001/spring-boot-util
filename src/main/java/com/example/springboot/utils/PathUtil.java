package com.example.springboot.utils;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @Auther: yewub
 * @Date: 2019/5/27 16:25
 * @Description:
 */
public class PathUtil {
    public static final String DEVASTATOR_HOME = "DEVASTATOR";
    public static final String BUMBLEBEE_HOME = "BUMBLEBEE";
    public static final String JAZZ_HOME = "JAZZ";

    private static final String PLUGINS_DIR = "plugins";
    private static final String CONTRACT_TEMPLATE_DIR = "contracts";
    private String home;
    private List<String> paths;

    /**
     * 获取大黄蜂插件目录
     *
     * @return
     */
    public static File bumblebeePluginsFolder() throws IOException {
        return new PathUtil().get(PLUGINS_DIR).toFile(PathUtil.BUMBLEBEE_HOME);
    }

    public static File jazzContractTemplateFolder() throws IOException {
        return new PathUtil().get(CONTRACT_TEMPLATE_DIR).toFile(PathUtil.JAZZ_HOME);
    }

    /**
     * 获取大力神插件目录
     *
     * @return
     */
    public static File devastatorPluginsFolder() throws IOException {
        return new PathUtil().get(PLUGINS_DIR).toFile(PathUtil.DEVASTATOR_HOME);
    }

    public PathUtil() {
        this(null, new ArrayList<>());
    }

    public PathUtil(List<String> paths) {
        this(null, paths);
    }

    public PathUtil(String home, List<String> paths) {
        this.home = home;
        this.paths = paths;
    }

    public void setHome(String home){
        this.home = home;
    }

    private String getHome(String moduleHome) {
        if (this.home == null) {
            String devastatorHome = System.getenv(moduleHome);
            this.home = devastatorHome == null ? System.getProperty("user.home") + "/" + StringUtils.lowerCase(moduleHome) : devastatorHome;
        }

        return this.home;
    }

    public Path path(String moduleName) {
        return Paths.get(this.getHome(moduleName), (String[]) this.paths.toArray(new String[0]));
    }

    /**
     * 相对路径
     *
     * @param moduleName
     * @return
     */
    public Path relativize(String moduleName) {
        return Paths.get(this.getHome(moduleName)).relativize(this.path(moduleName));
    }

    public File toFile(String moduleName) throws IOException {
        Path path = this.path(moduleName);
        if (Files.notExists(path)) {
            FileUtils.forceMkdir(path.toFile());
        }
        return path.toFile();
    }

    private PathUtil path(String path, String... more) {
        this.paths.add(path);
        Collections.addAll(this.paths, more);
        return this;
    }

    public PathUtil get(String... more) {
        Collections.addAll(this.paths, more);
        return this;
    }

    public static PathUtil root() {
        return new PathUtil();
    }

}
