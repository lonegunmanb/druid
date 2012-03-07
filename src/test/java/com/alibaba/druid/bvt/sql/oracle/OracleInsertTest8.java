package com.alibaba.druid.bvt.sql.oracle;

import java.util.List;

import junit.framework.Assert;

import com.alibaba.druid.sql.OracleTest;
import com.alibaba.druid.sql.ast.SQLStatement;
import com.alibaba.druid.sql.dialect.oracle.parser.OracleStatementParser;
import com.alibaba.druid.sql.dialect.oracle.visitor.OracleSchemaStatVisitor;
import com.alibaba.druid.stat.TableStat;

public class OracleInsertTest8 extends OracleTest {

    public void test_0() throws Exception {
        String sql = "insert into AV_INFO_NEW (ID, GMT_CREATE, GMT_MODIFIED, COMPANY_ID, COMPANY_NAME_CN, COMPANY_NAME_EN, COMPANY_COUNTRY, "
                     + " COMPANY_PROVINCE, COMPANY_CITY, COMPANY_ADDR_CN, COMPANY_ADDR_EN, MEMBER_SEX, MEMBER_CN_NAME, MEMBER_FIRST_NAME, MEMBER_LAST_NAME,"
                     + "  MEMBER_PHONE_COUNTRY, MEMBER_PHONE_AREA, MEMBER_PHONE_NUMBER, MEMBER_JOB_TITLE_CN, MEMBER_JOB_TITLE_EN, MEMBER_DEPT_EN, "
                     + " MEMBER_DEPT_CN, LINK_EMAIL, STATUS, AV_PROVIDER, AV_ORIGIN)"
                     + " values (1000236058, sysdate, sysdate, 1300904670, '��������5''TW'"
                     + ", 'Yunnan', 'sadf', '4r7V', 'fdgtg', 'M', '�����info_name4', 'Fnameinfo_name4'"
                     + ", '33', '4444', '6666', '���fgsgsdfg', 'fggtgth', '�������������', null, 'zeus'" + ")";

        OracleStatementParser parser = new OracleStatementParser(sql);
        List<SQLStatement> statementList = parser.parseStatementList();
        SQLStatement statemen = statementList.get(0);
        print(statementList);

        Assert.assertEquals(1, statementList.size());

        OracleSchemaStatVisitor visitor = new OracleSchemaStatVisitor();
        statemen.accept(visitor);

        System.out.println("Tables : " + visitor.getTables());
        System.out.println("fields : " + visitor.getColumns());
        System.out.println("coditions : " + visitor.getConditions());
        System.out.println("relationships : " + visitor.getRelationships());

        Assert.assertEquals(9, visitor.getTables().size());
        Assert.assertEquals(42, visitor.getColumns().size());

        // Assert.assertTrue(visitor.getTables().containsKey(new TableStat.Name("raises")));
        // Assert.assertTrue(visitor.getTables().containsKey(new TableStat.Name("employees")));
        //
        // Assert.assertTrue(visitor.getColumns().contains(new TableStat.Column("employees", "employee_id")));
        // Assert.assertTrue(visitor.getColumns().contains(new TableStat.Column("employees", "salary")));
        // Assert.assertTrue(visitor.getColumns().contains(new TableStat.Column("employees", "commission_pct")));
    }

}