namespace BankAccount.Test
{
    public sealed class Compte
    {
        public int Balance { get; }

        public static Compte CreateCompte()
        {
            return new Compte();
        }

        private Compte()
        {
            Balance = 0;
        }
        
        
    }
}