package com.ruoyi.app.mapper;
 
import java.sql.Array;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

import com.alibaba.fastjson.JSONObject;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedTypes;
import org.apache.ibatis.type.TypeException;
 
// 继承自BaseTypeHandler<Object[]> 使用时传入的参数一定要是Object[]，例如 int[]是 Object, 不是Object[]，所以传入int[] 会报错的
@MappedTypes({List.class})
public class ArrayTypeHandler extends BaseTypeHandler<List> {

    @Override
    public void setNonNullParameter(PreparedStatement preparedStatement, int i, List list, JdbcType jdbcType) throws SQLException {
        preparedStatement.setString(i, JSONObject.toJSONString(list));
    }

    @Override
    public List getNullableResult(ResultSet resultSet, String s) throws SQLException {
        System.out.println("---------------------\n\n\n\n-----------------");
        return null;
    }

    @Override
    public List getNullableResult(ResultSet resultSet, int i) throws SQLException {
        System.out.println("---------------------\n\n\n\n-----------------");
        return null;
    }

    @Override
    public List getNullableResult(CallableStatement callableStatement, int i) throws SQLException {
        System.out.println("---------------------\n\n\n\n-----------------");
        return null;
    }

}