package com.krokoq.simple.opentelemetry.rest.domain.db.entity;

import com.krokoq.simple.opentelemetry.rest.domain.ConvertibleItem;
import lombok.*;
import org.hibernate.proxy.HibernateProxy;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "item")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class ItemEntity implements ConvertibleItem {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "item_id_sequence_generator")
    @SequenceGenerator(name = "item_id_sequence_generator",
            sequenceName = "item_id_sequence",
            allocationSize = 1)
    @Column(name = "id")
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "content")
    private String content;

    @ToString.Exclude
    @Column(name = "create_date")
    private Date createDate;

    @ToString.Exclude
    @Column(name = "update_date")
    private Date updateDate;

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        Class<?> oEffectiveClass = o instanceof HibernateProxy ? ((HibernateProxy) o).getHibernateLazyInitializer().getPersistentClass() : o.getClass();
        Class<?> thisEffectiveClass = this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass() : this.getClass();
        if (thisEffectiveClass != oEffectiveClass) return false;
        ItemEntity that = (ItemEntity) o;
        return getId() != null && Objects.equals(getId(), that.getId());
    }

    @Override
    public final int hashCode() {
        return this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass().hashCode() : getClass().hashCode();
    }
}
