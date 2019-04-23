package br.com.caelum.ingresso.validacao;

import java.time.LocalTime;
import java.util.List;

import br.com.caelum.ingresso.model.Sessao;

public class ValidadorDeSessao {

    public boolean cabe(Sessao sessaoNova, List<Sessao> sessoesExistentes) {

        return sessoesExistentes.stream().noneMatch(sessaoExist -> isConflitante(sessaoNova, sessaoExist));

    }

    private boolean isConflitante(Sessao sessaoNova, Sessao sessaoExist) {
        LocalTime inicioSessaoNova = sessaoNova.getHorario();
        LocalTime terminoSessaoNova = sessaoNova.getHorario().plus(sessaoNova.getFilme().getDuracao());
        LocalTime inicioSessaoExist = sessaoExist.getHorario();
        LocalTime terminoSessaoExist = sessaoExist.getHorario().plus(sessaoExist.getFilme().getDuracao());

        boolean inicioNovaDentroExist = inicioSessaoNova.isAfter(inicioSessaoExist)
                && inicioSessaoNova.isBefore(terminoSessaoExist);
        boolean fimNovaDentroExist = terminoSessaoNova.isAfter(inicioSessaoExist)
                && terminoSessaoNova.isBefore(terminoSessaoExist);
        boolean inicioExistDentroNova = inicioSessaoExist.isAfter(inicioSessaoNova)
                && inicioSessaoExist.isBefore(terminoSessaoNova);
        boolean fimExistDentroNova = terminoSessaoExist.isAfter(inicioSessaoNova)
                && terminoSessaoExist.isBefore(terminoSessaoNova);
        if (inicioNovaDentroExist || fimNovaDentroExist || inicioExistDentroNova || fimExistDentroNova) {
            return true;
        }
        return false;
    }

}
