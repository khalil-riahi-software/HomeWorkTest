package fr.d2factory.libraryapp.util;

import fr.d2factory.libraryapp.member.Resident;

/**
 * this class ResidentBuilder can create easy and build Resident Object
 */
public class ResidentBuilder {

    private float wallet;
    private boolean late;

    public ResidentBuilder() {
    }

    public ResidentBuilder withWallet(float wallet){
        this.wallet=wallet;
        return this;
    }


    public ResidentBuilder islate(boolean late){
        this.late=late;
        return this;
    }
    public Resident build(){
        return new Resident(wallet,late);
    }

}
