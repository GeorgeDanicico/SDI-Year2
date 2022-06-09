export interface Actor {
  id: number,
  name: string,
  rating: number,
}

export interface Movie {
  id: number,
  title: string,
  description: string,
  genre: string,
}

export interface MovieDto {
  movies: Movie[],
  totalPages: number,
  totalItems: number,
  currentPage: number,
}


