package pl.raszkowski.generatortypetest.entity;

import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.MappedSuperclass;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@MappedSuperclass
public abstract class TestEntity {

    public String value1;

    public Date value2;

    public BigDecimal value3;
}
