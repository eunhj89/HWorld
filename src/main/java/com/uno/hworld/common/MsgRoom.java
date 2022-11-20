package com.uno.hworld.common;

import lombok.*;
import javax.persistence.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class MsgRoom {

    @Id
    private String id;

    @Column(nullable = false)
    private String roomId;

}
