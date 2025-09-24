package com.gaei.clientManager.domain.model;

import com.gaei.clientManager.domain.exception.ClientValidationException;

public enum DocumentType {
    TARJETA_DE_IDENTIDAD("TI"),
    CEDULA_DE_CIUDADANIA("CC"),
    CEDULA_DE_EXTRANJERIA("CE"),
    PASAPORTE("PA"),
    REGISTRO_CIVIL("RC");

    private final String type;

    DocumentType(String type){
        this.type = type;
    }

    public String getType(){
        return type;
    }

    public static DocumentType from(String type){
        for (DocumentType documentType : values()){
            if(documentType.type.equalsIgnoreCase(type)) {
                return documentType;
            }
        }
        throw new ClientValidationException(null,"Tipo de documento no valido: " + type);
    }
}
