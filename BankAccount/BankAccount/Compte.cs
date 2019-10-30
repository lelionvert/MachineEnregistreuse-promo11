using System.Collections.Generic;
using System.Transactions;

namespace BankAccount.Test
{
    public sealed class Compte
    {
        public int Balance { get; }
        public List<TransactionBancaire> Transactions { get; }

        public static Compte CreateCompte()
        {
            return new Compte();
        }

        private Compte()
        {
            Transactions = new List<TransactionBancaire>();
            Balance = 0;
        }
    }
}