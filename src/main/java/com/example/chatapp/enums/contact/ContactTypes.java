package com.example.chatapp.enums.contact;

import lombok.Getter;


@Getter
public enum ContactTypes {

    PRIVATE("private", 0, "Chat Participant", 2, 2),
    GROUP("group", 1, "Group Contact",  2),
    COMMON(2);

    private  String label;
    private  int code;
    private  String alternativeName;
    private  int maxMembers;
    private  int minMembers;

    ContactTypes(int minMembers)
    {
        this.minMembers=minMembers;
    }
    ContactTypes(String label, int code, String alternativeName, int maxMembers, int minMembers) {
        this.label = label;
        this.code = code;
        this.alternativeName = alternativeName;
        this.maxMembers = maxMembers;
        this.minMembers = minMembers;
    }
    ContactTypes(String label, int code, String alternativeName,  int minMembers) {
        this.label = label;
        this.code = code;
        this.alternativeName = alternativeName;
        this.minMembers = minMembers;
    }

}
