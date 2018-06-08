package com.littlemonkey.autocode.comment;

import com.littlemonkey.utils.collect.Collections3;
import com.littlemonkey.utils.lang.StringUtils;
import org.mybatis.generator.api.CommentGenerator;
import org.mybatis.generator.api.IntrospectedColumn;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.dom.java.*;
import org.mybatis.generator.api.dom.xml.XmlElement;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;

/**
 * @author xls
 */
public class MyCommentGenerator implements CommentGenerator {

    private ResourceBundle resourceBundle;

    private String[] modelImportAnnotations;

    private String[] interfaceImportAnnotations;


    public MyCommentGenerator() throws IOException {
        initResourceBundle();
        modelImportAnnotations = StringUtils.isNotBlank(resourceBundle.getString("model.import.annotation")) ? resourceBundle.getString("model.import.annotation").split(",") : null;
        interfaceImportAnnotations = StringUtils.isNotBlank(resourceBundle.getString("interface.import.annotation")) ? resourceBundle.getString("model.import.annotation").split(",") : null;
    }

    public void initResourceBundle() throws IOException {
        FileInputStream fileInputStream = new FileInputStream(this.getClass().getResource("/autocode/autocodeconfig.properties").getFile());
        resourceBundle = new PropertyResourceBundle(new BufferedInputStream(fileInputStream));
    }

    public void addConfigurationProperties(Properties properties) {

    }

    public void addFieldComment(Field field, IntrospectedTable introspectedTable, IntrospectedColumn introspectedColumn) {
        if (StringUtils.isNotBlank(introspectedColumn.getRemarks())) {
            StringBuilder sb = new StringBuilder();
            field.addJavaDocLine("/**");
            sb.append(" * ");
            sb.append(introspectedColumn.getRemarks());
            field.addJavaDocLine(sb.toString());
            field.addJavaDocLine(" */");
        }
    }

    public void addFieldComment(Field field, IntrospectedTable introspectedTable) {

    }

    public void addClassComment(InnerClass innerClass, IntrospectedTable introspectedTable) {

    }

    public void addClassComment(InnerClass innerClass, IntrospectedTable introspectedTable, boolean b) {


    }

    public void addEnumComment(InnerEnum innerEnum, IntrospectedTable introspectedTable) {

    }

    public void addGetterComment(Method method, IntrospectedTable introspectedTable, IntrospectedColumn introspectedColumn) {

    }

    public void addSetterComment(Method method, IntrospectedTable introspectedTable, IntrospectedColumn introspectedColumn) {

    }

    public void addGeneralMethodComment(Method method, IntrospectedTable introspectedTable) {

    }

    public void addJavaFileComment(CompilationUnit compilationUnit) {
        if (compilationUnit instanceof TopLevelClass) {
            TopLevelClass topLevelClass = (TopLevelClass) compilationUnit;
            if (Collections3.isNotEmpty(modelImportAnnotations)) {
                for (String annotation : modelImportAnnotations) {
                    topLevelClass.addAnnotation("@" + annotation);
                }
            }
        } else if (compilationUnit instanceof Interface) {
            Interface auInterface = (Interface) compilationUnit;
            if (Collections3.isNotEmpty(interfaceImportAnnotations)) {
                for (String annotation : interfaceImportAnnotations) {
                    auInterface.addAnnotation("@" + annotation);
                }
            }
        }
    }

    public void addComment(XmlElement xmlElement) {
    }

    public void addRootComment(XmlElement xmlElement) {

    }
}
