import {CgTriple} from './cg-triple';

export class CgPath {
  evidence?: CgTriple[];
  score: number;
  verbalization: string;
  sample: string;
  id?: number;

  constructor(evidence: CgTriple[], score: number, verbalization: string,sample: string, id: number) {
    this.evidence = evidence;
    this.score = score;
    this.verbalization = verbalization;
    this.sample = sample;
    this.id = id || 0;
  }
}
