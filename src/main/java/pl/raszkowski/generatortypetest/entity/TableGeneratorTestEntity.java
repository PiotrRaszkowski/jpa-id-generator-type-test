package pl.raszkowski.generatortypetest.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "TableGeneratorTest")
@Getter
@Setter
public class TableGeneratorTestEntity extends TestEntity {

    @Id
    @GeneratedValue(generator = "TestTableGenerator", strategy = GenerationType.TABLE)
    @TableGenerator(name = "TestTableGenerator", table = "TestTableGenerator", allocationSize = 1)
    private Long id;
}
