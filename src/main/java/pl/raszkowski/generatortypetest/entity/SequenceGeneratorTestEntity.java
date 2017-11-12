package pl.raszkowski.generatortypetest.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "SequenceGeneratorTest")
@Getter
@Setter
public class SequenceGeneratorTestEntity extends TestEntity {

    @Id
    @GeneratedValue(generator = "TestSequenceGenerator", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "TestSequenceGenerator", sequenceName = "TestSequenceGenerator", allocationSize = 1)
    private Long id;
}
