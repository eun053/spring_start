package eun.study.spring_start.domain;

import javax.persistence.*;

// ORM : Object와 Relational (RDB) Mapping
// @Entity : jpa가 관리하는 Entity
@Entity
public class Member {

    // @id : PK지정, @GeneratedValue : Identity
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // @Column : DB의 컬럼명 매핑
    @Column(name = "name")
    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
