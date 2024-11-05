package com.campusdual.classroom;

import java.text.Normalizer;

public class Contact implements ICallActions{
    private final String name;
    private final String surnames;
    private final String phone;

    public Contact(String name, String surnames, String phone) {
        this.name = name;
        this.surnames = surnames;
        this.phone = phone;
    }
    public String getName() {
        return this.name;
    }
    public String getSurnames() {
        return this.surnames;
    }
    public String getPhone() {
        return phone;
    }
    public String getCode() {

        String lowerName = this.name.toLowerCase();
        String lowerSurnames = this.surnames.toLowerCase();

        String diacriticsName = Normalizer.normalize(lowerName, Normalizer.Form.NFD);
        String diacriticsSurnames = Normalizer.normalize(lowerSurnames, Normalizer.Form.NFD);

        String perfectName = diacriticsName.replaceAll("[^\\p{ASCII}]", "");
        StringBuilder sb = getStringBuilder(diacriticsSurnames, perfectName);

        return sb.toString();
    }
    private static StringBuilder getStringBuilder(String diacriticsSurnames, String perfectName) {
        String perfectSurnames = diacriticsSurnames.replaceAll("[^\\p{ASCII}]", "");

        String[] nameParts = perfectName.split(" ");
        String[] surnamesPart = perfectSurnames.split(" ");

        StringBuilder sb = new StringBuilder();

        sb.append(nameParts[0].charAt(0));

        if(perfectSurnames.contains(" ")) {

            sb.append(surnamesPart[0].charAt(0));
            for (int i = 1; i < surnamesPart.length; i++) {
                sb.append(surnamesPart[i]);
            }
        }
        else {
            sb.append(perfectSurnames);
        }
        return sb;
    }
    @Override
    public void callMyNumber() {
        System.out.println("Llamandote a ti mismo..." + this.name + this.surnames + ", " + this.phone + ".");

    }
    @Override
    public void callOtherNumber(String number) {
        System.out.println("Llamando desde... " + this.name + " " + this.surnames + " a... " + number);

    }
    @Override
    public void showContactDetails() {
        System.out.println("Detalles del contacto: " + this.name + " " + this.surnames
                + " " + this.phone + " " + this.getCode());

    }
}