using System.Collections.Generic;
using System.Transactions;

namespace BankAccount.Test
{
    public sealed class Compte
    {
        private int Balance;
        private List<TransactionBancaire> Transactions;

        public static Compte CreateCompte()
        {
            Compte compte = new Compte();
            compte.Transactions = new List<TransactionBancaire>();
            compte.Balance = 0;
            return compte;
        }

        private Compte()
        {
        }

        public string ReleveDeCompte()
        {
            return "DATE | AMOUNT | BALANCE";
        }
    }
}