package up.code.codeup.mapper;

import up.code.codeup.entity.fase.Fase;

public class FaseMapper {
    public static Fase of(up.code.codeup.service.fase.dto.FaseCriacaoDto faseCriacaoDto) {
        Fase fase = new Fase();
        fase.setNum_fase(faseCriacaoDto.getNum_fase());
        fase.setNome(faseCriacaoDto.getNome());
        fase.setDescricao(faseCriacaoDto.getDescricao());
        fase.setConteudo_exec(faseCriacaoDto.getConteudo_exec());
        fase.setMateria(faseCriacaoDto.getMateria());

        return fase;
    }
}
