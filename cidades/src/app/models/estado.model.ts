import { Cidade } from "./cidade.model";

export class Estado {
    id!: number;
    nome!: string;
    sigla!: string;
    populacao?: number;
    capital?: Cidade;
    cidades?: Cidade[];
}
