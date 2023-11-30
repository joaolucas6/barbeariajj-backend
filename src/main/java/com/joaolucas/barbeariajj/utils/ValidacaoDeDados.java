package com.joaolucas.barbeariajj.utils;

import com.joaolucas.barbeariajj.models.dto.*;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.regex.Pattern;

public class ValidacaoDeDados {

    public static boolean servicoValido(ServicoDTO servicoDTO){
        if(todosOsAtributosSaoNulos(servicoDTO)) return false;
        if(servicoDTO.getNome() != null && servicoDTO.getNome().length() > 100 || servicoDTO.getNome() != null && servicoDTO.getNome().isBlank()) return false;
        if(servicoDTO.getDescricao() != null && servicoDTO.getDescricao().length() > 500 || servicoDTO.getDescricao() != null && servicoDTO.getDescricao().isBlank()) return false;
        if(servicoDTO.getPreco() != null && servicoDTO.getPreco().compareTo(BigDecimal.ZERO) == -1) return false;

        return true;
    }

    public static boolean pedidoAgendamentoValido(PedidoAgendamentoDTO pedidoAgendamentoDTO, boolean isUpdateRequest){
        if(todosOsAtributosSaoNulos(pedidoAgendamentoDTO)) return false;
        if(pedidoAgendamentoDTO.getHorarioInicio() != null && pedidoAgendamentoDTO.getHorarioInicio().isAfter(pedidoAgendamentoDTO.getHorarioFim())) return false;
        if(pedidoAgendamentoDTO.getExigenciasDoCliente() != null && pedidoAgendamentoDTO.getExigenciasDoCliente().length() > 500 || pedidoAgendamentoDTO.getExigenciasDoCliente() != null && pedidoAgendamentoDTO.getExigenciasDoCliente().isBlank() ) return false;

        if(!isUpdateRequest){
            if(pedidoAgendamentoDTO.getClienteId() == null || pedidoAgendamentoDTO.getBarbeiroId() == null) return false;
            if(pedidoAgendamentoDTO.getServicosId() == null) return false;
            if(pedidoAgendamentoDTO.getHorarioInicio() == null || pedidoAgendamentoDTO.getHorarioFim() == null) return false;
        }

        return true;
    }

    public static boolean userValido(ClienteDTO clienteDTO){
        if(todosOsAtributosSaoNulos(clienteDTO)) return false;
        if(clienteDTO.getNome() != null && clienteDTO.getNome().length() > 50 || clienteDTO.getNome() != null && clienteDTO.getNome().isBlank()) return false;
        if(clienteDTO.getSobrenome() != null && clienteDTO.getSobrenome().length() > 50 || clienteDTO.getSobrenome() != null && clienteDTO.getSobrenome().isBlank()) return false;
        if(!emailValido(clienteDTO.getEmail())) return false;
        if(clienteDTO.getNumeroTelefone() != null && clienteDTO.getNumeroTelefone().isBlank()) return false;
        if(!cpfValido(clienteDTO.getCpf())) return false;
        return true;
    }

    public static boolean userValido(BarbeiroDTO barbeiroDTO){
        if(todosOsAtributosSaoNulos(barbeiroDTO)) return false;
        if(barbeiroDTO.getNome() != null && barbeiroDTO.getNome().length() > 50 || barbeiroDTO.getNome() != null && barbeiroDTO.getNome().isBlank()) return false;
        if(barbeiroDTO.getSobrenome() != null && barbeiroDTO.getSobrenome().length() > 50 || barbeiroDTO.getSobrenome() != null && barbeiroDTO.getSobrenome().isBlank()) return false;
        if(!emailValido(barbeiroDTO.getEmail())) return false;
        if(barbeiroDTO.getNumeroTelefone() != null && barbeiroDTO.getNumeroTelefone().isBlank()) return false;
        if(!cpfValido(barbeiroDTO.getCpf())) return false;
        return true;
    }

    public static boolean userValido(AdminDTO adminDTO){
        if(todosOsAtributosSaoNulos(adminDTO)) return false;
        if(adminDTO.getNome() != null && adminDTO.getNome().length() > 50 || adminDTO.getNome() != null && adminDTO.getNome().isBlank()) return false;
        if(adminDTO.getSobrenome() != null && adminDTO.getSobrenome().length() > 50 || adminDTO.getSobrenome() != null && adminDTO.getSobrenome().isBlank()) return false;
        if(!emailValido(adminDTO.getEmail())) return false;
        if(adminDTO.getNumeroTelefone() != null && adminDTO.getNumeroTelefone().isBlank()) return false;
        if(!cpfValido(adminDTO.getCpf())) return false;
        return true;
    }

    public static boolean avaliacaoValido(AvaliacaoDTO avaliacaoDTO){
        if(todosOsAtributosSaoNulos(avaliacaoDTO)) return false;
        if(avaliacaoDTO.getNota() != null && avaliacaoDTO.getNota() < 0 || avaliacaoDTO.getNota() != null && avaliacaoDTO.getNota() > 10) return false;
        if(avaliacaoDTO.getComentarios() != null && avaliacaoDTO.getComentarios().length() > 500 || avaliacaoDTO.getComentarios() != null && avaliacaoDTO.getComentarios().isBlank()) return false;
        return true;
    }

    public static boolean agendamentoValido(AgendamentoDTO agendamentoDTO){
        if(todosOsAtributosSaoNulos(agendamentoDTO)) return false;
        if(agendamentoDTO.getPrecoAdicional() != null && agendamentoDTO.getPrecoAdicional().compareTo(BigDecimal.ZERO) == -1) return false;
        if(agendamentoDTO.getPrecoDescontado() != null && agendamentoDTO.getPrecoDescontado().compareTo(BigDecimal.ZERO) == -1) return false;
        return true;
    }

    public static boolean cpfValido(String valor){
        String cpf = valor.replace(".", "").replace("-", "");

        String primeiraParte = cpf.substring(0, 9);

        int resultadoDaPrimeiraParte = 0;

        for(int i = 10, j = 0; i >= 2; i--, j++){
            int numero = Integer.parseInt(String.valueOf(primeiraParte.charAt(j)));

            numero *= i;
            resultadoDaPrimeiraParte += numero;
        }

        if(resultadoDaPrimeiraParte * 10 % 11 !=  Integer.parseInt(String.valueOf(cpf.charAt(9)))) return false;

        int resultadoDaSegundaParte = 0;

        for(int i = 11, j = 0; i >= 2; i--, j++){

            int numero = Integer.parseInt(String.valueOf(cpf.charAt(j)));

            numero *= i;
            resultadoDaSegundaParte += numero;
        }

        if(resultadoDaSegundaParte * 10 % 11 != Integer.parseInt(String.valueOf(cpf.charAt(10)))) return false;

        return true;
    }

    public static boolean emailValido(String email){
        Pattern pattern = Pattern.compile("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$");
        return pattern.matcher(email).matches();
    }

    public static boolean todosOsAtributosSaoNulos(Object object){
        Class<?> objectClass = object.getClass();
        for(Field field : objectClass.getFields()){
            if(field == null) return true;
        }

        return false;
    }
}
