package fr.d2factory.libraryapp.member;

import fr.d2factory.libraryapp.Exception.SoldeInsuffisantMember;


public class Student extends Member {
    private static String EXCEPTION_SOLDE = "solde is insufficient";

    private int levelStudy;

    public Student(int levelStudy, float wallet, boolean late) {
        super(wallet, late);
        this.levelStudy = levelStudy;
    }

    @Override
    public void payBook(int numberOfDays) throws SoldeInsuffisantMember {
        if (numberOfDays > 0 && numberOfDays <= 30) {
            float price = 0;
            if (this.levelStudy > 1) {
                price = (10 * numberOfDays) / 100;
                verifMoneyOfMember(price);
            } else if (this.levelStudy == 1) {
                if (numberOfDays >= 15) {
                    price = (10 * (numberOfDays - 15) / 100);
                    verifMoneyOfMember(price);

                }
            }
        }
    }

    private void verifMoneyOfMember(float price) throws SoldeInsuffisantMember {
        if (this.wallet >= price) {
            this.wallet = this.wallet - price;
        } else {
            throw new SoldeInsuffisantMember(EXCEPTION_SOLDE);

        }
    }

}
