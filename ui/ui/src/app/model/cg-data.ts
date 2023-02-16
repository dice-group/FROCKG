import {CgTriple} from "./cg-triple";
import {CgPath} from "./cg-path";

export class CgData {
  piecesOfEvidence: CgPath[];
  veracityValue: number;
  fact: CgTriple;
  finalJudgement?: boolean;
}
