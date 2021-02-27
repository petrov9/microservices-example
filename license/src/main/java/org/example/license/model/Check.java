package org.example.license.model;

import javax.persistence.Id;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.hateoas.RepresentationModel;

@Getter
@Setter
@ToString
@RedisHash("check")
public class Check extends RepresentationModel<Check> {

    @Id
    private String id;
    private Boolean isExpired;
}
