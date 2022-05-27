export interface Team {
  id: number;
  name: string;
}

export interface TeamDto {
  teams: Team[],
  totalPages: number,
  totalItems: number,
  currentPage: number,
}
