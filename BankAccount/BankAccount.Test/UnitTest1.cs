using NUnit.Framework;
using NFluent;

namespace BankAccount.Test
{
    public class Tests
    {
        private Compte compte;

        [SetUp]
        public void Setup()
        {
        }

        [Test]
        public void CreatedAccountMustBeInitializedWith0OnBalanceAndEmptyTransactionList()
        {
            // Given / When
            compte = Compte.CreateCompte();

            // Then
            Check.That(compte.ReleveDeCompte()).IsEqualTo("DATE | AMOUNT | BALANCE");
        }
    }
}