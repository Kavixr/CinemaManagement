/**
 * Represents a person with a name, surname, and email.
 */
public class Person {
    public String name;
    public String surname;
    public String email;

    /**
     * Constructs a person with the specified name, surname, and email.
     *
     * @param name    the name of the person
     * @param surname the surname of the person
     * @param email   the email of the person
     */
    public Person(String name, String surname, String email){
        this.name = name;
        this.surname = surname;
        this.email = email;
    }
    /**
     * Retrieves the name of the person.
     *
     * @return the name of the person
     */
    public String getName(){
        return name;
    }

    /**
     * Sets the name of the person.
     *
     * @param name the new name of the person
     */
    public void setName(String name){
        this.name = name;
    }

    /**
     * Retrieves the surname of the person.
     *
     * @return the surname of the person
     */
    public String getSurname(){
        return surname;
    }

    /**
     * Sets the surname of the person.
     *
     * @param surname the new surname of the person
     */
    public void setSurname(String surname){
        this.surname = surname;
    }

    /**
     * Retrieves the email of the person.
     *
     * @return the email of the person
     */
    public String getEmail(){
        return email;
    }

    /**
     * Sets the email of the person.
     *
     * @param email the new email of the person
     */
    public void setEmail(String email){
        this.email = email;
    }


    /**
     * Prints the customer information of the person.
     * Includes name, surname, and email.
     */
    public void PersonInfo(){
        System.out.println("Name " + getName());
        System.out.println("Surname " + getSurname());
        System.out.println("email " + getEmail());
    }
}
