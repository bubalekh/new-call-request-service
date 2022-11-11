package pw.cyberbrain.service.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "request")
public class CallRequestDao {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    @Getter
    @Setter
    private Long id;

    @Column(name = "name")
    @Getter
    @Setter
    private String name;

    @Column(name = "phone", nullable = false)
    @Getter
    @Setter
    private String phone;

    @Column(name = "date", nullable = false)
    @Getter
    @Setter
    private String date;

    @Column(name = "time", nullable = false)
    @Getter
    @Setter
    private String time;

    @Column(name = "userId", nullable = false)
    @Getter
    @Setter
    private Long userId;
}
