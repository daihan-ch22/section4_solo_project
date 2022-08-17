package api.v1.member.entity;

import lombok.*;
import org.springframework.beans.factory.annotation.Required;

import javax.persistence.*;

/// STUB DATA ///


@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long memberId;

    @Column(nullable = false)
    private String userName;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String sex;

    @Column(nullable = false)
    private String company_name;

    @Column
    private String company_type;

    @Column
    private String company_location;
}
