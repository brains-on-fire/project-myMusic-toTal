package com.ciandt.summit.bootcamp2022.dto;

import com.ciandt.summit.bootcamp2022.entity.Musica;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MusicaDTO {

    private List<Musica> data;
}
