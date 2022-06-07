import {Address} from "../../addresses/shared/address.model";

export interface Project {
  id: number,
  name: string,
  description: string,
  createdBy: {
    id: number,
    name: string,
    dateOfBirth: Date,
    address: Address
  },
}

export interface ProjectDto {
  projects: Project[],
  totalPages: number,
  totalItems: number,
  currentPage: number,
}
