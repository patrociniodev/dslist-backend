package br.com.isaacpatrocinio.dslist_backend.domain.entities;

import br.com.isaacpatrocinio.dslist_backend.domain.pk.BelongingPK;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Table(name = "tb_belonging")
@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@RequiredArgsConstructor
public class Belonging {
    @EmbeddedId
    private BelongingPK id = new BelongingPK();
    private Integer position;
}
