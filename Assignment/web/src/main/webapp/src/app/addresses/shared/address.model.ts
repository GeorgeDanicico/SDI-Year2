export interface Address {
  id: number,
  city: string,
  street: string,
}

export interface AddressDto {
  addresses: Address[],
  totalPages: number,
  totalItems: number,
  currentPage: number,
}
