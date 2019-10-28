namespace CaisseEnregistreuse
{
    public interface IPriceQuery
    {
        Price FindPrice(string itemCode);
    }
}