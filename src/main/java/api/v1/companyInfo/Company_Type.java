package api.v1.companyInfo;

import javax.persistence.*;

@Entity
@Table(name = "company_type")
public class Company_Type {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long typeId;

}
