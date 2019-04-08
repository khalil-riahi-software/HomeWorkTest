package fr.d2factory.libraryapp.member;

import fr.d2factory.libraryapp.Exception.SoldeInsuffisantMember;

public class Resident extends Member {

private static String EXCEPTION_SOLDE="solde is insufficient";

    public Resident(float wallet, boolean late) {
        super(wallet, late);
    }

    @Override
    public void payBook(int numberOfDays)  throws SoldeInsuffisantMember {

        if(numberOfDays>0 && numberOfDays<=60) {
            float price=(10 * numberOfDays)/100;
            if (this.wallet >= price) {
                this.wallet=this.wallet- price;
            }else {
                throw new SoldeInsuffisantMember(EXCEPTION_SOLDE);
            }
        }
    }

}
