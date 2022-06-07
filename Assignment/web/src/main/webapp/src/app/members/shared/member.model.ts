export interface Member {
  id: number;
  name: string;
  dateOfBirth: Date;
  address: {
    id: number,
    city: string,
    street: string,
  }
}

export interface MemberDto {
  members: Member[],
  totalPages: number,
  totalItems: number,
  currentPage: number,
}
