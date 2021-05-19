package com.devsuperior.clientes.dto;

import com.devsuperior.clientes.entities.Client;

import javax.validation.constraints.*;
import java.io.Serializable;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;


public class ClientDTO  implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;

    @Size(min = 5, max = 60, message = "Deve ter entre 4 e 60 caracteres")
    @NotBlank(message = "Campo requerido")
    private String name;

    @NotBlank(message = "Campo requerido")
    private String cpf;

    @Positive(message = "Renda deve ser um valor positivo")
    private Double income;

    @PastOrPresent(message = "A data de nascimeto não pode ser futura")
    private Instant birthDate;
    
    @NotBlank(message = "Campo requerido")
    private Integer children;


    public ClientDTO() {
    }

    public ClientDTO(
            Long id,
            @Size(min = 5, max = 60, message = "Deve ter entre 4 e 60 caracteres")
            @NotBlank(message = "Campo requerido") String name,
            @NotBlank(message = "Campo requerido") String cpf,
            @Positive(message = "Renda deve ser um valor positivo") Double income,
            @PastOrPresent(message = "A data de nascimeto não pode ser futura") Instant birthDate,
            @NotBlank(message = "Campo requerido") Integer children) {
        this.id = id;
        this.name = name;
        this.cpf = cpf;
        this.income = income;
        this.birthDate = birthDate;
        this.children = children;
    }

    public ClientDTO( Client entity ) {
        this.id = entity.getId();
        this.name = entity.getName();
        this.cpf = entity.getCpf();
        this.income = entity.getIncome();
        this.birthDate = entity.getBirthDate();
        this.children = entity.getChildren();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public Double getIncome() { return income; }

    public void setIncome(Double income) { this.income = income; }

    public Instant getBirthDate() { return birthDate; }

    public void setBirthDate(Instant birthDate) { this.birthDate = birthDate; }

    public Integer getChildren() { return children; }

    public void setChildren(Integer children) { this.children = children; }
}
