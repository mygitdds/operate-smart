package com.shongnong.sp.resource.vo;
import lombok.Data;
import java.util.List;

@Data
public class GoodsGroup {
    private Long id;
    private Long enterpriseId;
    private List<Long> listGroup;
    private Double price;
    private String insertDate;
    private String updateDate;
}
