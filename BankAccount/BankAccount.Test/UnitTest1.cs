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
        public void Test1()
        {
            Check.That(true).IsTrue();
        }
    }
}