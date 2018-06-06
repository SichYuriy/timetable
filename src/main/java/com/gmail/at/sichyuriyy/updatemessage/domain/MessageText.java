package com.gmail.at.sichyuriyy.updatemessage.domain;

import lombok.*;

import javax.persistence.*;

@Data
@Entity
@Table(name = "message_text")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class MessageText {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Getter(AccessLevel.NONE)
    private String text;

    public String getStringValue() {
        return text;
    }
}
