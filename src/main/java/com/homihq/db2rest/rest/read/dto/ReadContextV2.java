package com.homihq.db2rest.rest.read.dto;


import com.homihq.db2rest.rest.read.model.DbColumn;
import com.homihq.db2rest.rest.read.model.DbJoin;
import com.homihq.db2rest.rest.read.model.DbTable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;


import java.util.List;
import java.util.Map;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Slf4j
public class ReadContextV2 {

    /* Input Attributes */
    String tableName;
    String fields;
    String filter;
    List<String> sorts;
    int limit;
    long offset;
    List<JoinDetail> joins;


    /* Attributes to replace the ones above */
    DbTable root;
    List<DbColumn> cols;
    String rootWhere;
    Map<String,Object> paramMap;
    List<DbJoin> dbJoins;

    public void addColumns(List<DbColumn> columnList) {
        this.cols.addAll(columnList);
    }
}
