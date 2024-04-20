// user.model.ts

export class User {
  id: number;
  name: string;
  firstName?: string;
  lastName?: string;
  dateOfBirth?: string;
  phoneNumber?: string;
  address?: string;
  email?: string;
  gender?: string;
  dni?: string;
  nickname?: string;
  roles?: string[];

  constructor(
    id: number,
    name: string,
    firstName?: string,
    lastName?: string,
    dateOfBirth?: string,
    phoneNumber?: string,
    address?: string,
    email?: string,
    gender?: string,
    dni?: string,
    nickname?: string,
    roles?: string[]
  ) {
    this.id = id;
    this.name = name;
    this.firstName = firstName;
    this.lastName = lastName;
    this.dateOfBirth = dateOfBirth;
    this.phoneNumber = phoneNumber;
    this.address = address;
    this.email = email;
    this.gender = gender;
    this.dni = dni;
    this.nickname = nickname;
    this.roles = roles;
  }
}
