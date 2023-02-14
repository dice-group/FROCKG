import {CgPath} from './cg-path';
import {CgTriple} from './cg-triple';

export class CgData {
  piecesOfEvidence: CgPath[];
  veracityValue: number;
  fact: CgTriple;
  finalJudgement?: boolean;
}
