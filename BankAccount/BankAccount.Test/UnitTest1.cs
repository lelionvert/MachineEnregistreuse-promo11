using NUnit.Framework;
using NFluent;

namespace BankAccount.Test
{
    public class Tests
    {
        [SetUp]
        public void Setup()
        {
        }

        [Test]
        public void CreatedAccountMustBeInitializedWith0OnBalance()
        {
            // Given / When
            Compte compte = Compte.CreateCompte();
            
            // Then
            Check.That(compte.Balance).IsEqualTo(0);
        }
    }
}