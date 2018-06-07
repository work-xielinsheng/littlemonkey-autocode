package com.littlemonkey.autocode.comment;

import com.littlemonkey.utils.lang.StringUtils;
import org.mybatis.generator.api.CommentGenerator;
import org.mybatis.generator.api.IntrospectedColumn;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.dom.java.*;
import org.mybatis.generator.api.dom.xml.XmlElement;

import java.util.Date;
import java.util.Properties;

public class MyCommentGenerator implements CommentGenerator {

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
        System.out.println("3");
    }

    public void addClassComment(InnerClass innerClass, IntrospectedTable introspectedTable) {
        System.out.println("???????????");
        StringBuilder sb = new StringBuilder();
        innerClass.addJavaDocLine("/**");
        sb.append(" * ");
        sb.append(introspectedTable.getFullyQualifiedTable());
        sb.append(" ");
        sb.append(new Date());
        innerClass.addJavaDocLine(sb.toString().replace("\n", " "));
        innerClass.addJavaDocLine(" */");
    }

    public void addClassComment(InnerClass innerClass, IntrospectedTable introspectedTable, boolean b) {
        System.out.println("??????????!!!!");
    }

    public void addEnumComment(InnerEnum innerEnum, IntrospectedTable introspectedTable) {

    }

    public void addGetterComment(Method method, IntrospectedTable introspectedTable, IntrospectedColumn introspectedColumn) {

    }

    public void addSetterComment(Method method, IntrospectedTable introspectedTable, IntrospectedColumn introspectedColumn) {

    }

    public void addGeneralMethodComment(Method method, IntrospectedTable introspectedTable) {
        System.out.println("2");
    }

    public void addJavaFileComment(CompilationUnit compilationUnit) {
        if (compilationUnit instanceof TopLevelClass) {
            TopLevelClass topLevelClass = (TopLevelClass) compilationUnit;
            topLevelClass.addAnnotation("@Data");
            topLevelClass.addImportedType("lombok.Data");
        }
    }

    public void addComment(XmlElement xmlElement) {
    }

    public void addRootComment(XmlElement xmlElement) {

    }
}
