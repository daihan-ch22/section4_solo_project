package api.v1.companyInfo;

import javax.persistence.*;

@Entity
@Table(name = "company_location")
public class Company_Location {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long locationId;

}
