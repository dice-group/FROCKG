import {CgTriple} from './cg-triple';
import {CgEvidence} from './cg-evidence';

export class CgPath {
  evidence?: CgTriple[];
  score: number;
  verbalization: string;
  id?: number;

  constructor(evidence: CgTriple[], score: number, verbalization: string, id: number) {
    this.evidence = evidence;
    this.score = score;
    this.verbalization = verbalization;
    this.id = id || 0;
  }
}
