export interface Actor {
  id: number,
  name: string,
  rating: number,
}

export interface Movie {
  id: number,
  title: string,
  year: number,
  actors: Actor[],
}
