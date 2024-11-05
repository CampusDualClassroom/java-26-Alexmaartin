package com.campusdual.classroom;


import com.campusdual.util.Utils;

import java.util.HashMap;
import java.util.Map;

public class Phonebook {
    private final Map<String,Contact> contacts;
    public Phonebook() {
        this.contacts = new HashMap<>();
    }
    public void menu() {
        int option;
        do {
            System.out.println("Menu");
            System.out.println("1. Añadir contacto");
            System.out.println("2. Contactos");
            System.out.println("3. Opciones del contacto");
            System.out.println("4. Borrar contacto");
            System.out.println("5. Salir");
            option = Utils.integer("Selecciona una opción: ");
            switch (option) {
                case 1:
                    String name = Utils.string("Nombre: ");
                    String surnames = Utils.string("Apellidos: ");
                    String phone = Utils.string("Número: ");

                    Contact contact = new Contact(name, surnames, phone);

                    addContact(contact);
                    break;
                case 2:
                    showPhonebook();
                    break;
                case 3:
                    String selected = Utils.string("Código del contacto: ");
                    selectContact(selected);
                    break;
                case 4:
                    String deleted = Utils.string("Código del contacto: ");
                    deleteContact(deleted);
                    break;
                default:
                    System.out.println("Opción no válida.");
                    break;
            }
        }
        while(option != 4);
    }
    public void addContact(Contact contact) {
        String code = contact.getCode();
        if(!contacts.containsKey(code)){
            contacts.put(code, contact);
            System.out.println("Contacto añadido");
        }
        else {
            System.out.println("Este contacto ya existe...");
        }
    }
    public void showPhonebook() {

        if (contacts.isEmpty()) {
            System.out.println("No hay contactos.");
        }
        else{
            System.out.println("Contactos: ");
            for (Map.Entry<String, Contact> entry : contacts.entrySet()) {
                System.out.println(entry.getValue().getName() + " " + entry.getValue().getSurnames() + " "
                        + entry.getValue().getPhone() + " " + entry.getValue().getCode());
            }
        }
    }
    public void selectContact(String contactCode) {

        for (Map.Entry<String, Contact> entry : contacts.entrySet()) {
            if(contactCode.equals(entry.getValue().getCode())) {
                Contact selectedContact = entry.getValue();
                contactMenu(selectedContact);
            }
            else {
                System.out.println("Código incorrecto");
            }
        }
    }
    public void deleteContact(String contactCode) {

        for (Map.Entry<String, Contact> entry : contacts.entrySet()) {
            if(contactCode.equals(entry.getValue().getCode())) {
                contacts.remove(entry.getKey());
                System.out.println("El contacto ha sido eliminado");

            }
            else {
                System.out.println("El código no es correcto");
            }
        }
    }
    public Map<String, Contact> getData() {

        Map<String, Contact> contactList = new HashMap<>();
        for (Map.Entry<String, Contact> entry : contacts.entrySet()) {
            String code = entry.getKey();
            Contact contact = entry.getValue();
            contactList.put(code,contact);
        }
        return contactList;
    }
    public void contactMenu(Contact contact){
        System.out.println("1. Llama a mi número");
        System.out.println("2. Llama a otro número");
        System.out.println("3. Info del contacto");
        int option = Utils.integer("Selecciona una opción: ");
        switch (option) {
            case 1:
                contact.callMyNumber();
                break;
            case 2:
                String number = Utils.string("Escribe el número: ");
                contact.callOtherNumber(number);
                break;
            case 3:
                contact.showContactDetails();
                break;
            default:
                System.out.println("Opción no válida.");
                break;
        }
    }
}
